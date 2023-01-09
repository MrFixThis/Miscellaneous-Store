import Fetcher from "./page_information.js"

const fetcher = new Fetcher()
const queryParams = fetcher.getQueryParameters()

let branchOffice = null
const branchOfficesData = await fetcher.getOrUpdateInformation({
	url: `${Fetcher.baseURL}/v1/branch_offices`
})
const adminsData = await fetcher.getOrUpdateInformation({
	url: `${Fetcher.baseURL}/v1/administrators`
})

const mainDataForm = $("#mainDataForm")

const renderTittleBadge = () => {
	let tittlePrefix = null
	switch(queryParams.operation) {
		case "create":
			tittlePrefix = "Register New"
			break
		case "update":
			tittlePrefix = "Update"
			break
		default:
			tittlePrefix = "See"
	}

	$("#tittleBadge").append(`
		<div class="container mt-4">
			<h1 class="h1 text-center">
				<span class="badge" style="background-color: #46E385">
					${tittlePrefix} Branch Office
				</span>
			</h1>
		</div>
	`)
}

const renderAdminSection = () => {
	const adminSection = mainDataForm.children("#adminSection")
	const isInspecting = queryParams.operation == null
	let areAdminsBusy = 0
	branchOffice = branchOfficesData.info.filter(branchOffice => {
		areAdminsBusy = branchOffice.administrator != null ? ++areAdminsBusy : areAdminsBusy
		if(branchOffice.id == queryParams.branchOfficeId) return branchOffice
	})[0]

	adminSection.append(`
		<h3 class="h3 text-muted text-center">
			<strong>
				Administrator
				${queryParams.operation == "update" && !branchOffice?.administrator
					? `Assignment`
					: `Specification`
				}
			</strong>
		</h3>
	`)

	if(adminsData.info.length != 0 || branchOffice?.administrator) {
		adminSection.append(`
			<div class="form-floating mt-3">
				<select name="administrator" class="form-select adminsSelector" id="floatingSelect" ${isInspecting ? "disabled" : ""}>
				</select>
				<label for="floatingSelect" class"text-center">
					${!isInspecting ? "Select one of them" : "Current administrator"}
				</label>
			</div>
		`)

		let adminsSelector = $(".adminsSelector")
		if(!isInspecting) {
			adminsSelector.append(`
				<option value="${branchOffice?.administrator.id}">
					${branchOffice?.administrator.firstName}
					${branchOffice?.administrator.middleName}
					${branchOffice?.administrator.paternalLastName}
					${branchOffice?.administrator.maternalLastName}
					- ${branchOffice?.administrator.role}
				</option>
			`)
		} else {
			adminsData.info.map(administrator => {
				adminsSelector.append(`
					<option value="${administrator.id}">
						${administrator.firstName}
						${administrator.middleName}
						${administrator.paternalLastName}
						${administrator.maternalLastName}
						- ${administrator.role}
					</option>
				`)
			})
		}
	} else {
		areAdminsBusy =  branchOfficesData.info.length == areAdminsBusy ? true : false
		adminSection.append(`
			<div class="container mt-4">
				<h5 class="h5 text-center text-muted">
					<em>
						${areAdminsBusy
							? `All the administrators are busy`
							: `There is no administrators registered`
						}
					</em>
				</h5>
				<div class="container mt-4 text-center">
					<form action="/administrators/actions">
						<input type="submit" value="Register New"
							class="btn btn-outline-primary btn-sm">
					</form>
				</div>
			</div>
		`)
	}
	if(queryParams.operation) mainDataForm.append(`<hr class="w-50 mx-auto">`)
}

const renderInventorySection = () => {
	if(queryParams.operation == "update" && !branchOffice?.administrator) return
	const inventorySection = mainDataForm.children("#inventorySection")

	inventorySection.append(`
		<h3 class="h3 text-muted text-center mt-4">
			<strong>Inventory ${queryParams.operation ? `Creation` : ``}</strong>
		</h3>
		<div class="text-success"><hr class="w-25 mx-auto"></div>
		<h5 class="h5 text-muted text-center mt-4">
			<strong>Description of the inventory</strong>
		</h5>
		<div class="container mt-4">
			<div class="form-floating">
			  <textarea name="description" class="TXT form-control" id="floatingTextarea2"
					style="resize:none; height: 100px" maxlength="255"
					${!queryParams.operation ? "readonly" : ""}>${branchOffice?.inventory.description}</textarea>
			  <label class="text-muted" for="floatingTextarea2">What's mainly in the inventory?</label>
			</div>
		</div>
		${queryParams.operation ? `<div class="text-success"><hr class="w-50 mx-auto"></div>` : ``}
	`)

	if(!queryParams.operation) {
		const productsIdentifiers = {
			magazine: "inventoryMagazineLots",
			book: "inventoryBookLots",
			disc: "inventoryDiscLots",
			vinylRecord: "inventoryVinylRecordLots"
		}
		let products = null

		inventorySection.append(`
			<div id="productSection" class="container justify-content-md-center">
				<h5 class="h5 text-muted text-center mt-4">
					<strong>Product List</strong>
				</h5>
				<hr class="w-25 mx-auto">
			</div>
		`)

		const productSection = inventorySection.children("#productSection")
		for(let pI in productsIdentifiers) {
			products = branchOffice?.inventory[productsIdentifiers[pI]]
			let isVinylReord = pI.includes("vinylRecord")

			if(products.length != 0) {
				productSection.append(`
					<table id="${pI}_table" class="table mt-4 table-bordered border rounded">
						<thead class="table-secondary text-center">
							<tr>
							  <th scope="col">${!(pI.includes("magazine") || pI.includes("book")) ? `ID` : `ISBN`}</th>
							  <th scope="col" class="col col-sm-5">${isVinylReord ? `Production ` : ``}Name</th>
							  <th scope="col">Available Units</th>
							  <th scope="col" class="col col-sm-3">Price P/U</th>
							  <th scope="col" ${queryParams.operation != "update" ? `style="display: none"` : ``}>Action</th>
							</tr>
						</thead>
						<tbody></tbody>
					</table>
					${isVinylReord ? `<hr class="w-75 mx-auto">` : ``}
				`)
				products.map(product => {
					$(`#${pI}_table`).children("tbody").append(`

					`)
				})
			} else {
				const productName = isVinylReord ? pI.replace("R", " r") : pI
				const urlName = `${productName.replace(/\s/, "_")}_lots`

				productSection.append(`
					<div class="container mt-4">
						<h5 class="h5 text-center text-muted mb-4">
							<em>There is no ${productName} lots available in the inventory</em>
						</h5>
					</div>
					<div class="container mt-4 mb-4 text-center">
						<form action="/${urlName}/actions?operation=create&inventoryId=${branchOffice.inventory.id}">
							<input type="submit" value="Add New Lot"
								class="btn btn-outline-primary">
						</form>
					</div>
				`)
			}
		}
	}
}

const renderMainActionButtons = () => {

}

$(() => {
	renderTittleBadge()
	if(branchOfficesData.err || adminsData.err) {
		console.error(boErr.err || adminErr.err)
	} else {
		renderAdminSection()
		renderInventorySection()
		renderMainActionButtons()
	}
})
