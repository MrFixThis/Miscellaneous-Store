package com.store.util;

/**
 * @author Bryan Baron
 */
@FunctionalInterface
public interface TransactionManager {

	/**
	 * Deletes the current Data model's entity before the deleting
	 * operation over itself. This method is used to truncate a possible
	 * post-delete re-persistence of the current entity.
	 */
	void preRemove();
}
