/*
 * This file is part of BORG.
 *
 * BORG is free software; you can redistribute it and/or modify it under the
 * terms of the GNU General Public License as published by the Free Software
 * Foundation; either version 2 of the License, or (at your option) any later
 * version.
 *
 * BORG is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR
 * A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with
 * BORG; if not, write to the Free Software Foundation, Inc., 59 Temple Place,
 * Suite 330, Boston, MA 02111-1307 USA
 *
 * Copyright 2003 by Mike Berger
 */
package net.sf.borg.model.entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Data;
import lombok.EqualsAndHashCode;



/**
 * The Tasklog Entity
 */
@XmlRootElement(name="Tasklog")
@XmlAccessorType(XmlAccessType.FIELD)
@Data
@EqualsAndHashCode(callSuper=true)
public class Tasklog extends KeyedEntity<Tasklog> {


	private static final long serialVersionUID = -7296390517941361874L;

	private java.util.Date logTime;
	private String Description;
	private Integer Task;
	

	/* (non-Javadoc)
	 * @see net.sf.borg.model.entity.KeyedEntity#clone()
	 */
	@Override
	protected Tasklog clone() {
		Tasklog dst = new Tasklog();
		dst.setKey( getKey());
		dst.setLogTime( getLogTime() );
		dst.setDescription( getDescription() );
		dst.setTask( getTask() );
		return(dst);
	}
}
