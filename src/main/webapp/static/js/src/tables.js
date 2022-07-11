$(document).ready(function() {
    $('#auction-table').DataTable({
        columnDefs: [
            {
                target: 3,
                visible: false
            },
            {
                target: 6,
                visible: false,
                searchable: false
            }
        ],
        order: [[6, 'desc']]
    });
});

$(document).ready(function () {
    $('#bid-table').DataTable({
        columnDefs: [
            {
                width: "35%",
                target: 0
            },
            {
                width: "30%",
                target: 1
            },
            {
                width: "35%",
                target: 2
            },
            {
                target: 3,
                visible: false,
                searchable: false
            }
        ],
        order: [[3, 'desc']]
    });
});