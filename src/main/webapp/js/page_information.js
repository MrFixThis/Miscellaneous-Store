export default class Fetcher {
	static baseURL
	static {
		this.baseURL = "/api"
	}

	constructor(config = null) {
		this.defaultConfig = {
			dataType: "json",
			url: "/api",
			type: "GET",
			...config
		}
	}

	obtainQueryParameters = () => {
		return new Proxy(new URLSearchParams(window.location.search), {
			get: (searchParams, prop) => searchParams.get(prop)
		})
	}

	sendOrGetInformation = async (config = null) => {
		const data = { info: null, err: null }
		try {
			data.info = await $.ajax(config || this.defaultConfig)
		} catch(error) { data.err = error }

		return data
	}
}
