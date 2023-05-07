# Appollo Router

## Usage

Rover muss verwendet werden um den Supergraph zu bauen. Mit folgendem Befehl kann man rover installieren

    npm install -g @apollo/rover

Folgender Befehl erzeugt den Supergraph

    rover supergraph compose --config ./config/supergraph.yaml > config/supergraph.graphqls

Man kann dann den Router mit dem docker compose file starten.

Wenn man einen Graphql client verwendet kann man z.B. an folgende url http://localhost:4000 die Beispiel query senden

    {
        shows {
            title
        }
    }

## Links

* https://www.apollographql.com/docs/router/quickstart
* https://www.apollographql.com/docs/router/containerization/overview/
* https://github.com/apollographql/router