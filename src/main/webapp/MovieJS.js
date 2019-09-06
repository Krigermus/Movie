window.onload = function () {

    function getAll() {
        let url = "http://localhost:8080/Movie/api/Movie/getAllMovies";
        startFetch(url, "all");
    }

    function getData() {
        let url = "http://localhost:8080/Movie/api/Movie/databaseData";
        startFetch(url, "data");
    }


    function startFetch(url, type) {
        fetch(url)
            .then(res => res.json())
            .then(data => {
                switch (type) {
                    case "all":
                        document.getElementById("data").innerHTML = '';
                        document.getElementById("movie").innerHTML =
                            "<table><thead><tr><th>Id</th><th>Year</th><th>Name</th><th>Actors</th></tr></thead><tbody>" +
                            data.map(x => "<tr><td>" + x.id + "</td><td>" + x.year + "</td><td>" + x.name + "</td><td>" + x.actors.join(", ") + "</td></tr>").join('');
                        + "</tbody></table>";
                        break;
                    case "single":
                        document.getElementById("data").innerHTML = '';
                        document.getElementById("movie").innerHTML =
                            "<table><thead><tr><th>Id</th><th>Year</th><th>Name</th><th>Actors</th></tr></thead><tbody>" +
                            data.map(x => "<tr><td>" + x.id + "</td><td>" + x.year + "</td><td>" + x.name + "</td><td>" + x.actors.join(", ") + "</td></tr>").join('');
                        + "</tbody></table>";
                        break;
                    case "data":
                        document.getElementById("movie").innerHTML = '';
                        document.getElementById("data").innerHTML =
                            data.msg;
                        break;
                }
            })
    }
    document.getElementById("getAllMovies").onclick = getAll;
    document.getElementById("getMovieById").onclick = getAll;
    document.getElementById("getMovieByName").onclick = getAll;
    document.getElementById("getCount").onclick = getAll;
    document.getElementById("createData").onclick = getData;

}


