// create an array with nodes
let arbol;
let network;

$.getJSON("arbol.json", function(datos) {
    arbol = datos;
    console.log(arbol);
    let nodes = [];
    let edges = [];
    let pos = 0;

    arbol.forEach(nodo => {
        let level = getLevelNodo(nodo);
        let color = "#000CFF";
        let colorHighlight = "#02066C";
        if (nodo.resultado === "si") {
            color = "#49FF00";
            colorHighlight = "#249400";
        } else if (nodo.resultado === "no") {
            color = "#FF0000";
            colorHighlight = "#C51F1F";
        } else if (nodo.resultado === "No hay datos") {
            color = "#ECFF00";
            colorHighlight = "#FFC300";
        }
        nodes.push({
            id: pos,
            label: nodo.resultado,
            heightConstraint: 40,
            title: "Nodo " + pos,
            level: level,
            color: { background: color, highlight: { background: colorHighlight } },
            font: { color: "#FFFFFF" }
        });

        if (nodo.padre !== -1) {
            edges.push({
                from: nodo.padre,
                to: pos,
                label: nodo.camino
            });
        }
        pos++;
    });

    // create a network
    let container = document.getElementById('visualization');
    let data = {
        nodes: nodes,
        edges: edges
    };

    let options = {
        width: "" + window.innerWidth + "px",
        height: "" + window.innerHeight + "px",
        edges: { length: 700 },
        physics: { enabled: false },
        layout: { hierarchical: true },
    }
    network = new vis.Network(container, data, options);
    console.log(network.getSeed());
});

function getLevelNodo(nodo) {
    let level = 0;
    let aux = nodo;

    while (aux.padre != -1) {
        level++;
        aux = arbol[aux.padre];
    }
    return level;
}