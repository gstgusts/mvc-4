let columnDefs = [
    {headerName: "Number", field: "number", sortable: true, filter:true },
    {headerName: "Vin number", field: "vinNumber", sortable: true, filter: true },
    {headerName: "Owner", field: "owner.name", sortable: true, filter: true }
];

let gridOptions = {
    columnDefs: columnDefs,
    //rowData: rowData
};

$(document).ready(function() {
    $('#loader').css('display','block');
    // loadData();

    let eGridDiv = document.querySelector('#myGrid');

    new agGrid.Grid(eGridDiv, gridOptions);

    agGrid.simpleHttpRequest({url: 'http://localhost:8080/api/cars'}).then(function(data) {
        gridOptions.api.setRowData(data);
        $('#loader').css('display','none');
    });
});

function loadData() {
    const settings = {
        "url": "http://localhost:8080/api/cars",
        "method": "GET",
        "timeout": 1000
    }

    $.ajax(settings).done(function (response) {
        $('#loader').css('display','none');
        setTableData(response);
    });
}

function searchCars(){
    $('#loader').css('display','block');

    agGrid.simpleHttpRequest({url: 'http://localhost:8080/api/cars?number='+$('#number').val()+'&vinNumber='+$('#vinNumber').val()}).then(function(data) {
        gridOptions.api.setRowData(data);
        $('#loader').css('display','none');
    });

    // let searchData = {
    //     number : $('#number').val(),
    //     vin_number : $('#vinNumber').val()
    // };
    //
    // let settings = {
    //     url: "http://localhost:8080/api/cars",
    //     method: "POST",
    //     timeout: 1000,
    //     headers: {
    //         "Content-Type": "application/json"
    //     },
    //     data: JSON.stringify(searchData)
    // }
    //
    // $.ajax(settings).done(function (response) {
    //     $('#loader').css('display','none');
    //     console.log(response);
    //     setTableData(response);
    //     //gridOptions.api.setRowData(response);
    // });

}

function setTableData(response) {

    $('#carData').empty();

    response.forEach(function(car) {
        //$('#carData').append('<li>'+car.number+'</li>');

        let row = '<tr>';
        row += '<td><a href="/cars/'+car.id+'">'+car.number+'</a></td>';
        row += '<td>'+car.vinNumber+'</td>';

        let owner = '';

        if(car.owner != undefined) {
            owner = car.owner.name;
        } else {
            owner = '&nbsp;';
        }

        row += '<td>'+owner+'</td>';

        row += '</tr>';

        $('#carData').append(row);
    });
}