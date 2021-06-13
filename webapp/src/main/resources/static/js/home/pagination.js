class PageNavigator {

    constructor() {
        this._current = 0;
        this._displayable = 1;
        this._total = 0;

        this._navBarElement = document.getElementById('pages-nav-bar');
        this._nextBtnElement = document.getElementById('next-btn');
        this._prevBtnElement = document.getElementById('prev-btn');
        this._currentPageElement = document.getElementById('current-page');

        this.update();
    }

    get current() {
        return this._current;
    }

    next() {
        if (this._current < this._total) {
            this._current++;
            this._displayable++;

            this.update();
        }
    }

    prev() {
        if (this._current > 0) {
            this._current--;
            this._displayable--;

            this.update();
        }
    }

    update() {
        this.resetTotal();
        this.resetNextBtn();
        this.resetPrevBtn();
        this.updatePageCounter();
    }

    resetTotal() {
        this._total = Number.parseInt(
            this._navBarElement.getAttribute('total-pages')
        );
    }

    resetNextBtn() {
        if (this._current < this._total - 1) {
            this._nextBtnElement.removeAttribute('disabled');
            this._nextBtnElement.classList.remove('btn-inactive');
        } else {
            this._nextBtnElement.setAttribute('disabled', '');
            this._nextBtnElement.classList.add('btn-inactive');
        }
    }

    resetPrevBtn() {
        if (this._current > 0) {
            this._prevBtnElement.removeAttribute('disabled');
            this._prevBtnElement.classList.remove('btn-inactive');
        } else {
            this._prevBtnElement.setAttribute('disabled', '');
            this._prevBtnElement.classList.add('btn-inactive');
        }
    }

    updatePageCounter() {
        this._currentPageElement.textContent = this._displayable;
    }

}

pageNav = new PageNavigator();


(function () {

    let prevBtn = document.getElementById('prev-btn');
    let nextBtn = document.getElementById('next-btn');


    prevBtn.addEventListener(
        'click',
        () => {
            this.pageNav.prev();
            pageBtnClick(this.pageNav.current)
        }
    );

    nextBtn.addEventListener(
        'click',
        () => {
            this.pageNav.next();
            pageBtnClick(this.pageNav.current)
        }
    );
})();


function pageBtnClick(pageNumber) {

    let request = new XMLHttpRequest();

    request.open(
        'GET',
        'get-page?page=' + pageNumber,
        true
    );

    request.onreadystatechange = function () {

        if (request.readyState === 4 && request.status === 200) {
            jsonToFlights(request.responseText);
        }
    };

    request.send();
}


function jsonToFlights(jsonCollection) {

    let collection = JSON.parse(jsonCollection);
    let flightsList = document.getElementById('flights-list');

    if (collection.length > flightsList.children.length) {
        while (collection.length !== flightsList.children.length) {
            flightsList.appendChild(
                flightsList.lastElementChild.cloneNode(true)
            );
        }
    }

    let recycledAmount = recycleElements(collection);

    if (recycledAmount < flightsList.children.length) {
        while (collection.length !== flightsList.children.length) {
            flightsList.removeChild(flightsList.lastElementChild);
        }
    }

}


function recycleElements(collection) {

    let flightsList = document.getElementById('flights-list');

    let i = 0;
    for (const flightJson of collection) {
        let li = flightsList.children[i];
        let flight = JSON.parse(flightJson);

        li.querySelector('.item-id-block').textContent = flight.id;
        li.querySelectorAll('.info-block__info')[0].textContent =
            flight.departurePoint + ' — ' + flight.arrivalPoint;
        li.querySelectorAll('.info-block__info')[1].textContent =
            flight.departureDateTime + ' — ' + flight.arrivalDateTime;
        li.querySelectorAll('.info-block__info')[2].textContent =
            flight.status;
        li.querySelector('button').onclick = function () {
            location.assign('flight-edit/' + flight.id)
        };

        i++;
    }

    // Returns the amount of recycled elements.
    return i;
}
