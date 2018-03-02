/*
 This file is part of BORG.

 BORG is free software; you can redistribute it and/or modify
 it under the terms of the GNU General Public License as published by
 the Free Software Foundation; either version 2 of the License, or
 (at your option) any later version.

 BORG is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU General Public License for more details.

 You should have received a copy of the GNU General Public License
 along with BORG; if not, write to the Free Software
 Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA

 Copyright 2003 by Mike Berger
 */
/*
 * popups.java
 *
 * Created on January 16, 2004, 3:08 PM
 */

package net.sf.borg.ui.popup;

import java.util.ArrayList;
import java.util.List;

import net.sf.borg.model.Model.ChangeEvent;

/**
 * A Reminder List Manager. This class manages a list of reminder instances and
 * updates that list periodically to hold reminders that are being show or that
 * have been shown and have been hidden by the user. It also reacts to model
 * change events if appointments are deleted. It also manages a ReminderList UI
 * object to show the reminders
 */
public class ReminderListManager extends ReminderManager {

	/**
	 * Gets the singleton.
	 * 
	 * @return the singleton
	 */
	public static ReminderManager getReference() {
		if (singleton == null) {
			singleton = new ReminderListManager();
		}
		return singleton;
	}

	// the UI
	private ReminderList reminderList = new ReminderList();

	// managed reminder instances
	private List<ReminderInstance> reminders = new ArrayList<ReminderInstance>();

	/**
	 * constructor
	 */
	private ReminderListManager() {
		super();
	}

	@Override
	public void addToUI(ReminderInstance instance) {

		// skip appt if it is already in the reminders list
		// this means that it is already showing - or was shown
		// and hidden already
		if (reminders.contains(instance))
			return;

		reminders.add(instance);

		// do not refresh UI - that will happen during periodicUpdate()
	}

	/**
	 * get the list of reminder instances
	 * 
	 * @return the reminder instances
	 */
	public List<ReminderInstance> getReminders() {
		return reminders;
	}

	/**
	 * Hide the reminder list window
	 */
	@Override
	public void hideAll() {
		reminderList.setVisible(false);
	}

	@Override
	/**
	 * check the list of reminders to see if any are changing. this would be due to a reminder 
	 * reaching a new user-tunable reminder time or an untimed todo getting its half-hour update
	 */
	public void periodicUpdate() {

		boolean needUpdate = false;

		// iterate through existing popups
		for (ReminderInstance reminderInstance : reminders) {

			// skip hidden reminders
			if (reminderInstance.isHidden())
				continue;

			// untimed todo
			if (reminderInstance.isNote() && reminderInstance.isTodo()) {

				if (!reminderInstance.isShown()
						|| shouldShowUntimedTodosNow()) {
					needUpdate = true;
					break;
				}

			} else if (reminderInstance.dueForPopup() != -1) {
				needUpdate = true;
				break;
			}

		}

		if (needUpdate)
			reminderList.refresh();
		else if (reminderList.isShowing()) {
			reminderList.updateTimes();
		}

	}

	@Override
	public void update(ChangeEvent event) {
		refresh();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.sf.borg.model.Model.Listener#refresh()
	 */
	@Override
	public void refresh() {

		// list of keys to reminders that no longer need to be shown
		ArrayList<ReminderInstance> deletedReminders = new ArrayList<ReminderInstance>();

		// loop through the existing reminders
		for (ReminderInstance reminderInstance : reminders) {

			// check if db has changed
			if (reminderInstance.reloadAndCheckForChanges())
				deletedReminders.add(reminderInstance);

			else if (!reminderInstance.shouldBeShown()) {
				// dispose of popup and add to delete list
				deletedReminders.add(reminderInstance);
			}

		}

		// delete the popup map entries for reminders that we disposed of
		for (ReminderInstance inst : deletedReminders) {
			reminders.remove(inst);
		}

		// refresh the list, but don't show unless it is already showing
		if (!deletedReminders.isEmpty())
			reminderList.refresh(!reminderList.isShowing());

	}

	/**
	 * stop the timer and remove all popups
	 */
	@Override
	public void remove() {
		super.remove();

		reminderList.destroy();
	}

	/**
	 * show the list
	 */
	@Override
	public void showAll() {
		reminderList.setVisible(true);
		reminderList.toFront();
	}

}
