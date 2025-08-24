# RDF Generator & Ontology Manager (JavaFX + Apache Jena)

A JavaFX desktop application for managing RDF files and ontologies. Built with Apache Jena for RDF parsing, reasoning, querying, and TDB2 storage. Comes with a modular UI, SPARQL console, SHACL validation, and upcoming graph visualization support.

---

## 🚀 Features

### Core Functionality (Current)
- **RDF / OWL Handling**: Load, view, edit, and save ontologies using Apache Jena (RDF/XML, Turtle, JSON-LD, N-Triples).
- **Persistent Storage**: Local triple store powered by Jena TDB2 for project workspaces.
- **SPARQL Console**: Run SPARQL SELECT, CONSTRUCT, ASK queries; view results as tables, JSON, or RDF.
- **Reasoning & Validation**: OWL/RDFS inferencing with Jena Reasoners; SHACL validation support using Jena SHACL.
- **Import/Export Tools**: Batch import/export in multiple RDF formats; GraphML/GEXF export for external graph tools.

### Upcoming Enhancements
- **Interactive Graph Visualization**  
  - Embed **Cytoscape.js** (via JavaFX `WebView`) or native tools like **GraphStream** or **JGraphX** to render RDF as interactive graphs (nodes and edges).
  - Enable functionalities like subgraph filtering, expand-on-click, pathfinding, and community detection.
- **Property-Graph Integration (Optional)**  
  - Export RDF to **Neo4j** with **neosemantics (n10s)** or other LPG systems (Gremlin/TinkerPop) for advanced graph analytics using Cypher or Gremlin.
- **Graph Algorithms & Analytics**  
  - Run PageRank, centrality, community detection via Jena + NetworkX or Neo4j GDS.
  - Optionally support GNN-based analytics using PyTorch Geometric or DGL—bring predictions back as RDF annotations.
- **Enhanced UX**  
  - Dockable UI panes, dark/light themes, smart autocomplete with prefix support, “why-inferred” reasoning overlays, and “find usages” navigation.
- **Testing & CI/CD**  
  - JUnit 5 tests, golden-file RDF round-trip validation.
  - CI builds using GitHub Actions with native installers via `jpackage` for Windows, macOS, and Linux.

---

## 🛠 Tech Stack

| Layer             | Technologies / Tools                                         |
|------------------|-------------------------------------------------------------|
| UI & Frontend     | JavaFX, ControlsFX, RichTextFX, embedded WebView           |
| RDF & Ontologies  | Apache Jena (RDF I/O, OntModel), SHACL, Jena TDB2          |
| Querying          | SPARQL with Jena ARQ                                       |
| Visualization     | Cytoscape.js (WebView), GraphStream, JGraphX, vis-network  |
| Graph Integration | Neo4j + neosemantics (n10s), JanusGraph / Gremlin          |
| Analytics         | Neo4j GDS, NetworkX, PyTorch Geometric / DGL               |
| Build & Packaging | Maven or Gradle, jlink / jpackage for native installers     |
| Testing & CI      | JUnit 5, golden-file tests, GitHub Actions                 |

---

## 🗺 Project Roadmap

Project Storage Enhancements

Add TDB2-backed project management for multiple ontology workspaces.

Validation & Reasoning Panel

UI for SHACL validation results, inference toggles, and editing suggestions.

Graph Visualization

SPARQL CONSTRUCT → JSON → Cytoscape.js visualization with interactive layout and filtering.

Property-Graph Export (Optional)

RDF → LPG using Neo4j + n10s or Gremlin pipelines for alternative views and analytics.

Graph Analytics

Embed GDS algorithms (e.g., PageRank) and display results as node properties within the RDF graph.

Advanced Insights (Phase 2)

Leverage GNNs for link prediction or classification; integrate results back into RDF with provenance.


---

## ⚡ Getting Started

### Clone the repo


git clone https://github.com/zakarich/RDF_Generator.git

cd RDF_Generator


### Build the project


mvn clean package

or with Gradle

gradle build


### Run the app


mvn javafx:run


### Run tests


mvn test


### Package native installers

Use jpackage via Maven/Gradle to generate platform-specific installers

---

## 🤝 Contribution



Contributions are very welcome!
Whether you want to help with graph visualization, add new validation features, improve UX, write tests,
or integrate graph analytics—feel free to file issues or open PRs.

Please follow the standard GitHub workflow:

Fork the repo

Create a feature branch

Commit your changes with clear messages

Push the branch and open a PR

Ensure tests are included for new functionality


---

## 📬 Contact



Maintained by: @zakarich (https://github.com/zakarich
)

Feel free to reach out for:

Feature discussions

Collaboration requests

Project ideas or enhancements


---

## 📄 License

MIT License

Copyright (c) 2025 zakarich

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
