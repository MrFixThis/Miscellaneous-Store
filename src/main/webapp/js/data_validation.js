//DOM components
let docFields = [
    Array.from(document.getElementsByClassName("TXT")),
    Array.from(document.getElementsByClassName("NBR")),
]
let exceptedFields = [
    "emailAddress", "residenceAddress", "authorName", "discFormat",
    "birthDate", "hireDate", "publicationDate"
]
let submitBtn = document.getElementById("sbtn")

//Regex patterns
const regex = [/[^A-Za-z0-9 \n]/, /\D/]

/**
    * Validates the information of all the form's fields
*/
const validateField = () => {
    let isInvalidValue = false
    let emptyField = 0

    for(const i in regex) {
        for(const field of docFields[i]) {
            if(isInvalidValue) { break }
            isInvalidValue = exceptedFields.includes(field.getAttribute("name"))
                ? false : regex[i].test(field.value);
            if(/^$/.test(field.value)) { ++emptyField }
        }
    }

    submitBtn.disabled = (isInvalidValue || emptyField > 0) ? true : false;
}

//Event setting
Array.from(docFields).forEach((fields) => {
    fields.forEach((field) => {
        field.addEventListener("input", validateField)
    })
})
