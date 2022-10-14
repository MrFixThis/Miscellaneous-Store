package co.edu.unbosque.controller;

import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import co.edu.unbosque.entity.Administrator;
import co.edu.unbosque.entity.BranchOffice;
import co.edu.unbosque.entity.Inventory;
import co.edu.unbosque.service.impl.AdministratorServiceImpl;
import co.edu.unbosque.service.impl.BranchOfficeServiceImpl;
import co.edu.unbosque.service.impl.InventoryServiceImpl;
import lombok.AllArgsConstructor;

/**
 * @author Bryan Baron
 */
@Controller
@AllArgsConstructor
public class BranchOfficeController {

	private BranchOfficeServiceImpl branchOfficeServiceImpl;
	private AdministratorServiceImpl administratorServiceImpl;
	private InventoryServiceImpl inventoryServiceImpl;

	/**
	 *
	 */
	@GetMapping("/branch_offices/create")
	public String registerNewBranchOffice(Model model) {
		List<Administrator> administrators =
			administratorServiceImpl.getAdministrators().getBody();
		Iterator<Administrator> aIterator = administrators.iterator();
		boolean allBusy = false;

		if(administrators.size() > 0) {
			while(aIterator.hasNext())
				if(aIterator.next().getBranchOffice() != null) aIterator.remove();
			if(administrators.size() == 0) allBusy = true;
		}

		model.addAttribute("action", "post");
		model.addAttribute("administrators", administrators);
		model.addAttribute("allBusy", allBusy);

		return "branchOfficeActions";
	}

	/**
	 *
	 */
	@GetMapping("/branch_offices/manage/create")
	public String createBranchOffice(Inventory newInventory,
			@RequestParam(name = "administratorId") Long administratorId) {
		BranchOffice newBranchoffice = new BranchOffice();
		Administrator boAdministrator = administratorServiceImpl
			.getAdministratorById(administratorId).getBody();

		newBranchoffice.setAdministrator(boAdministrator);
		newBranchoffice.setInventory(newInventory);
		branchOfficeServiceImpl.createBranchOffice(newBranchoffice);

		return "redirect:/branch_offices";
	}


	/**
	 *
	 */
	@GetMapping({"/", "/branch_offices"})
	public String showBranchOffices(Model model) {
		List<BranchOffice> branchOffices =
			branchOfficeServiceImpl.getBranchOffices().getBody();
		model.addAttribute("branchOffices", branchOffices);

		return "branchOffices";
	}

	/**
	 *
	 */
	@GetMapping("/branch_offices/{id}")
	public String showBranchOffice(@PathVariable(name = "id") Long id,
			Model model) {
		BranchOffice branchOffice =
			branchOfficeServiceImpl.getBranchOfficeById(id).getBody();

		model.addAttribute("action", "get");
		model.addAttribute("branchOffice", branchOffice);

		return "branchOfficeActions";
	}

	/**
	 *
	 */
	@GetMapping("/branch_offices/update/{id}")
	public String updateBranchOffice(@PathVariable(name = "id") Long id,
			Model model) {
		List<Administrator> administrators =
			administratorServiceImpl.getAdministrators().getBody();
		BranchOffice branchOffice =
			branchOfficeServiceImpl.getBranchOfficeById(id).getBody();
		Iterator<Administrator> aIterator = administrators.iterator();
		boolean allBusy = false;

		if(administrators.size() > 0) {
			while(aIterator.hasNext())
				if(aIterator.next().getBranchOffice() != null) aIterator.remove();
			if(administrators.size() == 0) allBusy = true;
		}

		model.addAttribute("action", "put");
		model.addAttribute("branchOffice", branchOffice);
		model.addAttribute("administrators", administrators);
		model.addAttribute("allBusy", allBusy);

		return "branchOfficeActions";
	}

	/**
	 *
	 */
	@GetMapping("/branch_offices/manage/update/{id}")
	public String updateBranchOffice(Inventory updatedInventory,
			@PathVariable(name = "id") Long id,
			@RequestParam(name = "administratorId", required = false)
				Long administratorId) {

		if(administratorId != null) {
			BranchOffice updatedBranchOffice =
				branchOfficeServiceImpl.getBranchOfficeById(id).getBody();
			Administrator newAdministrator =
				administratorServiceImpl.getAdministratorById(administratorId)
				.getBody();
			updatedBranchOffice.setAdministrator(newAdministrator);
			branchOfficeServiceImpl.updateBranchOfficeById(id, updatedBranchOffice);
		} else {
			Inventory currentInventory =
				branchOfficeServiceImpl.getBranchOfficeById(id).getBody()
					.getInventory();
			inventoryServiceImpl.updateInventoryById(currentInventory.getId(),
					updatedInventory);
		}

		return String.format("redirect:/branch_offices/%d", id);
	}

	/**
	 *
	 */
	@PostMapping("/branch_offices/manage/delete/{id}")
	public String deleteBranchOffice(@PathVariable(name = "id") Long id) {
		branchOfficeServiceImpl.deleteBranchOfficeById(id);
		return "redirect:/branch_offices";
	}
}
