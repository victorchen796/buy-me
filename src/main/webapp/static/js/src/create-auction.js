let detailCount = 1;
let tagCount = 1;

function addDetail() {
    if (detailCount === 10) return;
    detailCount++
    $('#detail-row' + detailCount).show();
}

function removeDetail(row) {
    if (row === 1 && detailCount === row) {
        $('#field' + detailCount).val(null);
        $('#value' + detailCount).val(null);
    } else {
        for (let i = row; i < detailCount; i++) {
            $('#field' + i).val($('#field' + (i + 1)).val());
            $('#value' + i).val($('#value' + (i + 1)).val());
        }
        $('#field' + detailCount).val(null);
        $('#value' + detailCount).val(null);
        $('#detail-row' + detailCount).hide();
        detailCount--;
    }
}

function addTag() {
    if (tagCount === 20) return;
    tagCount++;
    $('#tag-row' + tagCount).show();
}

function removeTag(row) {
    if (row === 1 && tagCount === row) {
        $('#tag' + tagCount).val(null);
    } else {
        for (let i = row; i < tagCount; i++) {
            $('#tag' + i).val($('#tag' + (i + 1)).val());
        }
        $('#tag' + tagCount).val(null);
        $('#tag-row' + tagCount).hide();
        tagCount--;
    }
}

$(function() {
    for (let i = 2; i <= 10; i++) {
        $('#detail-row' + i).hide();
    }

    for (let i = 2; i <= 20; i++) {
        $('#tag-row' + i).hide();
    }

    const title = $('#title');
    const titleError = $('#title-error');
    const description = $('#description');
    const descriptionError = $('#description-error');
    const condition = $('#condition');
    const type = $('#type');
    const typeError = $('#type-error');
    const details = [];
    const detailsError = [];
    for (let i = 1; i < 10; i++) {
        details.push({
            field: $('#field' + i),
            value: $('#value' + i)
        });
        detailsError.push({
            field: $('#field' + i + '-error'),
            value: $('#value' + i + '-error')
        });
    }
    const tags = [];
    for (let i = 1; i <= 20; i++) {
        tags.push($('#tag' + i));
    }
    const price = $('#price');
    const increment = $('#increment');
    const expiration = $('#expiration');
    const expirationError = $('#expiration-error');

    let error = false;

    $('#form').on('submit', function(event) {
        let data = validateForm();
        if (error) {
            event.preventDefault();
        } else {
            $('#data').html(JSON.stringify(data));
        }
    });

    function validateForm() {
        error = false;

        let titleValue = title.val().trim();
        let descriptionValue = description.val().trim();
        let conditionValue = condition.val();
        let typeValue = type.val().trim();
        let detailsValue = [];
        for (let i = 0; i < details.length; i++) {
            detailsValue.push({
                field: details[i].field.val().trim(),
                value: details[i].value.val().trim()
            })
        }
        let tagsValue = [];
        for (let i = 0; i < tags.length; i++) {
            tagsValue.push(tags[i].val().trim());
        }
        let priceValue = price.val();
        let incrementValue = increment.val();
        let expirationValue = expiration.val();

        let detailsReturn = [];
        let tagsReturn = [];

        if (titleValue === '') {
            title.addClass('input-error');
            titleError.html('Title cannot be blank.');
            error = true;
        } else {
            title.removeClass('input-error');
            titleError.html('');
        }
        if (descriptionValue === '') {
            description.addClass('input-error');
            descriptionError.html('Description cannot be blank.');
            error = true;
        } else {
            description.removeClass('input-error');
            descriptionError.html('');
        }
        if (typeValue === '') {
            type.addClass('input-error');
            typeError.html('Type cannot be blank.');
            error = true;
        } else {
            type.removeClass('input-error');
            typeError.html('');
        }
        for (let i = 0; i < detailsValue.length; i++) {
            if ((detailsValue[i].field === '') ^ (detailsValue[i].value === '')) {
                if (detailsValue[i].field === '') {
                    details[i].field.addClass('input-error');
                    details[i].value.removeClass('input-error');
                    detailsError[i].field.html('Field cannot be blank while value is not blank.');
                    detailsError[i].value.html('');
                } else {
                    details[i].field.removeClass('input-error');
                    details[i].value.addClass('input-error');
                    detailsError[i].field.html('');
                    detailsError[i].value.html('Value cannot be blank while field is not blank.');
                }
                error = true;
            } else {
                if (detailsValue[i].field !== '') {
                    detailsReturn.push({
                        field: detailsValue[i].field,
                        value: detailsValue[i].value
                    });
                }
                details[i].field.removeClass('input-error');
                details[i].value.removeClass('input-error');
                detailsError[i].field.html('');
                detailsError[i].value.html('');
            }
        }
        for (let i = 0; i < tagsValue.length; i++) {
            if (tagsValue[i] !== '') {
                tagsReturn.push(tagsValue[i]);
            }
        }
        if (expirationValue === undefined || expirationValue === '') {
            expiration.addClass('input-error');
            expirationError.html('Expiration cannot be blank');
            error = true;
        } else if (new Date(expirationValue) < new Date()) {
            expiration.addClass('input-error');
            expirationError.html('Expiration must be after now.');
            error = true;
        } else {
            expiration.removeClass('input-error');
            expirationError.html('');
        }

        let data = {};
        if (!error) {
            data.title = titleValue;
            data.description = descriptionValue;
            data.condition = conditionValue;
            data.type = typeValue;
            data.details = detailsReturn;
            data.tags = tagsReturn;
            data.price = priceValue;
            data.increment = incrementValue;
            data.expiration = new Date(expirationValue).getTime();
        }

        return data;
    }
});