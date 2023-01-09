import Fetcher from "./page_information.js"

const fetcher = new Fetcher({
	url: `${Fetcher.baseURL}/v1/branch_offices`
})

const $boCards = $("#cardContainer")

const renderBranchOfficesCards = async () => {
	const branchOfficesData = await fetcher.getOrUpdateInformation()
	if(branchOfficesData.err) { console.error(branchOfficesData.err); return }

	if(branchOfficesData.info?.lenght != 0) {
		branchOfficesData.info.map(branchOffice => {
			let isWithoutAdmin = branchOffice.administrator == null
			$boCards.append(`
				<div class="col-md-auto">
					<div class="card text-center mb-4" style="width: 18rem; background-color: ${isWithoutAdmin ? "#FFEFD4" : "#FAEFFF"}">
					  <div class="card-body">
						<h5 class="card-title">Branch Office #${branchOffice.id}</h5>
						${isWithoutAdmin
							? `<p class="card-text">Without administration</p>`
							: `<p class="card-text">Managed by ${branchOffice.administrator?.firstName}
								${branchOffice.administrator.paternalLastName}</p>`
						}
						<a href="/branch_offices/actions?${isWithoutAdmin ? `operation=update&` : ``}branchOfficeId=${branchOffice.id}"
							class="btn btn-${isWithoutAdmin ? `warning` : `primary`}">
							${isWithoutAdmin ? `Assign administrator` : `See more`}
						</a>
					  </div>
					</div>
				</div>
			`)
		})
	} else {
		$boCards.append(`
			<div class="container mt-4">
				<h3 class="h3 text-center text-muted">
					<em>There is no branch offices active</em>
				</h3>
				<div class="container mt-4 text-center">
					<form action="/branch_offices/actions?operation=create">
						<input type="submit" value="Register New"
							class="btn btn-outline-primary">
					</form>
				</div>
			</div>
		`)
	}
}

$(renderBranchOfficesCards)
