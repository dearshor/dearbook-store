package com.dearshor.dearbook.domain;

import javax.persistence.Embeddable;

@Embeddable
public class Press {
	private String pressName;

	public Press() {
		super();
	}

	public Press(String pressName) {
		super();
		this.pressName = pressName;
	}

	public String getPressName() {
		return pressName;
	}

	public void setPressName(String name) {
		this.pressName = name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((pressName == null) ? 0 : pressName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Press other = (Press) obj;
		if (pressName == null) {
			if (other.pressName != null)
				return false;
		} else if (!pressName.equals(other.pressName))
			return false;
		return true;
	}
}