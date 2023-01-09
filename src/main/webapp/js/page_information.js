export default class Fetcher {
	static baseURL = "/api"
	constructor(config = null) {
		this.defaultConfig = {
			dataType: "json",
			url: `${this.baseURL}`,
			type: "GET",
			...config
		}
	}

	getQueryParameters = () => {
		return new Proxy(new URLSearchParams(window.location.search), {
			get: (searchParams, prop) => searchParams.get(prop)
		})
	}

	getOrUpdateInformation = async (config = null) => {
		const data = {}
		try {
			data.info = await $.ajax(config || this.defaultConfig)
		} catch(error) { data.err = error }

		return data
	}
}
