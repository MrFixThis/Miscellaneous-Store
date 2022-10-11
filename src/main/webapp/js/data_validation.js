let cmnFields = [
    Array.from(document.getElementsByClassName("TXT")),
    Array.from(document.getElementsByClassName("NBR"))
]
let dateFields = [
    Array.from(document.getElementsByClassName("DTD")),
    Array.from(document.getElementsByClassName("DTM")),
    Array.from(document.getElementsByClassName("DTY"))
]
let submitBtn = document.getElementById("sbtn")

/**
    * Validates the information for all the form's fields
*/
const fieldValidation = () => {
    let regexCmn = [/[^A-Za-z0-9 ]/, /\D/]
    let regexDt = [
        /\b((0|[1-2])?[1-9]|3[0-1])\b/,
        /\b(0?[1-9]|1[0-2])\b/,
        /\b[1-2][0-9][0-9][0-9]\b/
    ]
    let isBadFieldValue = false
    let empty = 0

    for (const i in regexCmn) {
        for (const field of cmnFields[i]) {
            if(field.getAttribute("name") === "emailAddress" ||
                field.getAttribute("name") === "residenceAddress") { continue }

            if(isBadFieldValue) { break }
            if(regexCmn[i].test(field.value)) { isBadFieldValue = true }
            if(field.value == 0 || field.value === "") { empty++ }
        }
    }

    for (const i in regexDt) {
        for (const field of dateFields[i]) {
            if(isBadFieldValue) { break }
            if(!regexDt[i].test(field.value)) { isBadFieldValue = true }
            if(field.value === "" || /\D/.test(field.value)) { empty++ }
        }
    }

    submitBtn.disabled = false
    if(isBadFieldValue || empty > 0) { submitBtn.disabled = true }
}

//Events setting
Array.from(cmnFields).forEach((fields) => {
    fields.forEach((field) => {
        field.addEventListener("input", fieldValidation)
    })
})

Array.from(dateFields).forEach((fields) => {
    fields.forEach((field) => {
        field.addEventListener("input", fieldValidation)
    })
})
