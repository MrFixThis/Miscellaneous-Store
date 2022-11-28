let docFields = [
    Array.from(document.getElementsByClassName("TXT")),
    Array.from(document.getElementsByClassName("NBR")),
]
let exceptedFields = [
    "emailAddress", "residenceAddress", "authorName", "discFormat",
    "brithDate", "hireDate"
]
let submitBtn = document.getElementById("sbtn")

/**
    * Validates the information for all the form's fields
*/
const fieldValidation = () => {
    let regex = [/[^A-Za-z0-9 \n]/, /\D/]
    let isBadFieldValue = false
    let empty = 0

    for(const i in regex) {
        for(const field of docFields[i]) {
            if(exceptedFields.includes(field.getAttribute("name"))) { continue }

            if(isBadFieldValue) { break }
            if(regex[i].test(field.value)) { isBadFieldValue = true }
            if(field.value == 0 || field.value === "") { empty++ }
        }
    }

    submitBtn.disabled = false
    if(isBadFieldValue || empty > 0) { submitBtn.disabled = true }
}

//Events setting
Array.from(docFields).forEach((fields) => {
    fields.forEach((field) => {
        field.addEventListener("input", fieldValidation)
    })
})
