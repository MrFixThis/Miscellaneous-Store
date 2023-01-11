import Fetcher from "./page_information.js"

const fetcher = new Fetcher({ url: `${Fetcher.baseURL}/v1/branch_offices` })

const $branchOfficesCards = $("#cardContainer")

const renderBranchOfficesCards = async () => {
	const branchOfficesData = await fetcher.sendOrGetInformation()
	if(branchOfficesData.err) { throw branchOfficesData.err }

	let url = null
	const setClickEvent = (url, nbr) => {
		const element = `#actionBtn${nbr ? `_${nbr}` : ``}`
		$(element).on("click", function(_) {
			location.href = url
		})
	}

	if(branchOfficesData.info?.lenght != 0) {
		let isWithoutAdmin = null
		branchOfficesData.info.map(branchOffice => {
			isWithoutAdmin = branchOffice.administrator == null
			url = `/branch_offices/actions?${isWithoutAdmin ? `operation=update&` : ``}branchOfficeId=${branchOffice.id}`

			$branchOfficesCards.append(`
				<div class="col-md-auto">
					<div class="card text-center mb-4" style="width: 18rem; background-color: ${isWithoutAdmin ? "#FFEFD4" : "#FAEFFF"}">
					  <div class="card-body">
						<h5 class="card-title">Branch Office #${branchOffice.id}</h5>
						${isWithoutAdmin
							? `<p class="card-text">Without administration</p>`
							: `<p class="card-text">Managed by ${branchOffice.administrator?.firstName}
								${branchOffice.administrator.paternalLastName}</p>`
						}
						<button id="actionBtn_${branchOffice.id}" class="btn btn-${isWithoutAdmin ? `warning` : `primary`}"
							type="button">${isWithoutAdmin ? `Assign administrator` : `See more`}</button>
					  </div>
					</div>
				</div>
			`)
			setClickEvent(url, branchOffice.id)
		})
	} else {
		url = "/branch_offices/actions?operation=create"

		$branchOfficesCards.append(`
			<div class="container mt-4">
				<h3 class="h3 text-center text-muted">
					<em>There is no branch offices active</em>
				</h3>
				<div class="container mt-4 text-center">
					<button id="actionBtn" class="btn btn-outline-primary"
						type="button">Register New</button>
				</div>
			</div>
		`)
		setClickEvent(url)
	}
}

$(renderBranchOfficesCards)
