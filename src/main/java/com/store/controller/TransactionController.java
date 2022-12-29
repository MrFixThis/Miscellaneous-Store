package com.store.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Bryan Baron
 */
@Controller
@RequestMapping("/transactions")
public class TransactionController {

	/**
	 * Shows the Transactions' home page.
	 *
	 * @param branchOfficeId id of the BranchOffice entity related to the
	 * Transaction entities being manipulated.
	 * @return the Transaction's home template.
	 */
	@GetMapping
	public String showTransactionsHomePage(
			@RequestParam(name = "branchOfficeId") Long branchOfficeId) {
		return "transactions";
	}

	/**
	 * Shows the Transactions' actions page.
	 *
	 * @param branchOfficeId id of the BranchOffice entity related to the
	 * Transaction entity being manipulated.
	 * @param transactionId id of the Transaction entity to manipulate.
	 * @return the Transaction's actions template.
	 */
	@GetMapping("/actions")
	public String showTransactionsActionsPage(
			@RequestParam(name = "branchOfficeId") Long branchOfficeId,
			@RequestParam(name = "transactionId") Long transactionId) {
		return "transactionActions";
	}
}
