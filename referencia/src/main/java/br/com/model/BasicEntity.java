package br.com.model;

import java.io.Serializable;

public abstract class BasicEntity<TipoId> implements Serializable {

	private static final long serialVersionUID = -8450958866214552817L;

	public abstract TipoId getId();

	public abstract void setId(TipoId id);

	@SuppressWarnings("unchecked")
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final BasicEntity<TipoId> other = (BasicEntity<TipoId>) obj;
		if (getId() == null) {
			if (other.getId() != null)
				return false;
		} else if (!getId().equals(other.getId()))
			return false;
		return true;
	}

	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
		return result;
	}
}
