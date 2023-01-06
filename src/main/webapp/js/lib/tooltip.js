const tooltipTriggerList = Array.from(
    document.querySelectorAll("[data-bs-toggle='tooltip']")
)

//Events setting
tooltipTriggerList.map((tooltipTriggerEl) => {
  return new bootstrap.Tooltip(tooltipTriggerEl)
})
