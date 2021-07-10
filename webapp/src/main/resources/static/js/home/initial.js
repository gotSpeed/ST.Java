(function () {

    let newBtn = document.getElementById('new-btn');
    let flightsList = document.getElementById('flights-list');


    newBtn.addEventListener(
        'click',
        () => location.assign('new-flight-registration')
    );


    for (const li of flightsList.children) {

        let editBtn = li.getElementsByTagName('button')[0];
        let editableId = li.getElementsByClassName('item-id-block')[0].textContent;

        editBtn.onclick = function () {
            location.assign('flight-edit/' + editableId)
        };
    }
})();