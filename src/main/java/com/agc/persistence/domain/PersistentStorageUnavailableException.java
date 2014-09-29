package com.agc.persistence.domain;

import java.io.IOException;

/**
 * @author Dmitry Lekhtuz
 *
 */
public class PersistentStorageUnavailableException extends RuntimeException {
	private static final long serialVersionUID = -3174383814388032526L;

	public PersistentStorageUnavailableException(IOException e)
	{
		super(e);
	}
}
