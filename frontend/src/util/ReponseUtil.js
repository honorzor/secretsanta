export function handleCreateGameResponse(response) {
    return handleResponse(
        response,
        "Игра успешно создана",
        "Ошибка при создании игры \n" + response.data.message
    )
}

export function handleResponse(response, successMessage, errorMessage) {
    if (response.data.fail) {
        return errorMessage
    }
    return successMessage
}
