package com.geonwoo.solokill.global.db;

import static org.springframework.transaction.support.TransactionSynchronizationManager.*;

import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

@Profile("!test")
public class RoutingDataSource extends AbstractRoutingDataSource {

	@Override
	protected Object determineCurrentLookupKey() {
		return isCurrentTransactionReadOnly() ? "slave" : "master";
	}
}
