import Fetcher from "./page_information.js"

const fetcher = new Fetcher()

const $pageTittleBadge = $("#mainTittleBadge").children(".badge")
const $adminActionZone = $("#adminActionZone")
const $inventoryInfoArea = $("#inventoryInfoArea").children("textarea")

const renderDefaultConent = async () => {
	const adminsData = await fetcher.sendOrGetInformation({
		url: `${Fetcher.baseURL}/v1/administrators`
	})
	if(adminsData.err) { throw adminErr.err }

	const $createOrUpdateBtn = $("#createOrUpdateBtn")
	let $adminSelect = null
	let url = null

	if(adminsData.info.length != 0) {
		let admins = ``

		$adminActionZone.html(`
			<select name="administrator" class="form-select" id="floatingSelect"></select>
			<label for="floatingSelect" class"text-center">Select one of them</label>
		`)
		$adminSelect = $adminActionZone.children("select")

		adminsData.info.map(admin => {
			admins += `
				<option value="${admin.id}"> ${admin.firstName} ${admin.middleName}
					${admin.paternalLastName} ${admin.maternalLastName} - ${admin.role}
				</option>
			`
		})
		$adminSelect.append(admins)
	} else {
		url = "/administrators/actions"
		$adminActionZone.html(`
			<div class="container mt-4">
				<h5 class="h5 text-center text-muted">
					<em>There is no administrators registered</em>
				</h5>
				<div class="container mt-4 text-center">
				<button id="adminActionBtn" type="button"
					class="btn btn-outline-primary btn-sm">Register New</button>
				</div>
			</div>
		`)
		$("#adminActionBtn").on("click", function(_) { location.href = url })
		$createOrUpdateBtn.prop("disabled", true)
		$inventoryInfoArea.prop("readonly", true)
	}

	$createOrUpdateBtn.on("click", function(event) {
		event.preventDefault()

		const adminId = $adminSelect.val()
		const inventoryDesc = $inventoryInfoArea.val()

		const newInventory = fetcher.sendOrGetInformation({
			dataType: "json",
			url: `${Fetcher.baseURL}/v1/inventories`,
			type: "POST",
			data: {
				description: inventoryDesc
			}
		})
		if(newInventory.err) { throw newInventory.err }

		const newBranchOffice = fetcher.sendOrGetInformation({
			dataType: "json",
			url: `${Fetcher.baseURL}/v1/branch_offices`,
			type: "POST",
			data: {
				administrator: adminId,
				inventory: newInventory.info.id
			}
		})
		if(newBranchOffice.err) { throw newBranchOffice.err }

		url = `/branch_offices/actions?branchOfficeId=${newBranchOffice.info.id}`
		location.replace(url)
	})
}

const displayCurrentBranchOfficeInfo = async () => {
	const queryParams = fetcher.obtainQueryParameters()
	const branchOfficeData = await fetcher.sendOrGetInformation({
		url: `${Fetcher.baseURL}/v1/branch_offices/${queryParams.branchOfficeId}`
	})
	if(branchOfficeData.err) { throw branchOfficeData.err }

	renderProductSection(branchOfficeData.info)
}

const renderProductSection = (branchOffice) => {
	const $productSection = $("#productSection")
	const clickEvent = function(event) { location.href = event.data.url }
	const productsIdentifiers = {
		magazine: "inventoryMagazineLots",
		book: "inventoryBookLots",
		disc: "inventoryDiscLots",
		vinyl_record: "inventoryVinylRecordLots"
	}
	let products = null
	let productName = null
	let isVinylReord = null
	let hasISBN = false
	let url = null

	$productSection.append(`
		<h5 class="h5 text-muted text-center mt-4">
			<strong>Product List</strong>
		</h5>
		<hr class="w-25 mx-auto text-success">
	`)

	for(let pI in productsIdentifiers) {
		products = branchOffice.inventory[productsIdentifiers[pI]]
		isVinylReord = pI.includes("vinyl_record")
		hasISBN = pI.includes("magazine") || pI.includes("book")
		productName = isVinylReord ? pI.replace("_", " ") : pI
		url = `/${pI}_lots/actions?inp=true&inventoryId=${branchOffice.inventory.id}`

		if(products.length != 0) {
			url = url.replace(/inp=.*&/, "")

			$productSection.append(`
				<table id="${pI}_table" class="table mt-4 table-bordered border rounded">
					<thead class="table-secondary text-center">
						<tr>
						  <th scope="col">${hasISBN ? `ISBN` : `ID`}</th>
						  <th scope="col" class="col col-sm-5">${isVinylReord ? `Production ` : ``}Name</th>
						  <th scope="col">Available Units</th>
						  <th scope="col" class="col col-sm-3">Price P/U</th>
						  <th scope="col" class="actionColumn">Action</th>
						</tr>
					</thead>
					<tbody></tbody>
				</table>
				<hr class="w-75 mx-auto text-success">
			`)
			products.map(product => {
				$(`#${pI}_table`).children("tbody").append(`
					<tr class="actionColumn">
						<th scope="row" class="text-center">${hasISBN ? product.isbn : product.id}</th>
						  <td>${isVinylReord ? product.recordProductionName : product.name}</td>
						  <td>${product.availableUnits}</td>
						  <td>${product.pricePerUnit}</td>
						  <td class="text-center">
								<button id="product_actionBtn_${product.id}" type="button"
									class="btn btn-primary btn-sm">See more</button>
						  </td>
					</tr>
				`)
				$(`#product_actionBtn_${product.id}`).on("click", {url: url}, clickEvent)})
		} else {
			$productSection.append(`
				<div class="container mt-4">
					<h5 class="h5 text-center text-muted mb-4">
						<em>There is no ${productName} lots available in the inventory</em>
					</h5>
				</div>
				<div id="${pI}_actionBtn" class="container mt-4 mb-4 text-center">
					<button type="button" class="btn btn-outline-primary">Add new Lot</button>
				</div>
				<hr class="w-75 mx-auto text-success">
			`)
			$(`#${pI}_actionBtn`).on("click", {url: url}, clickEvent)
		}
	}
}

$(renderDefaultConent)
