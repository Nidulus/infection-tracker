import React, {Component} from 'react';
import mapboxgl from 'mapbox-gl';
import '../style/mapstyling.css'

mapboxgl.accessToken = 'pk.eyJ1IjoibmlkdWx1cyIsImEiOiJjazhrOXlmMXQwM2FlM2ZuYWpwYWExbDRkIn0.5oHOea-UKaE9WqviPnV3HA';

class Map extends Component {
  constructor(props) {
  super(props);
    this.state = {
      lng: 15.390722,
      lat: 63.075164,
      zoom: 4.3
    };
  }

  componentDidMount() {
    const map = new mapboxgl.Map({
      container: this.mapContainer,
      style: 'mapbox://styles/mapbox/streets-v11',
      center: [this.state.lng, this.state.lat],
      zoom: this.state.zoom
    });

    map.on('move', () => {
      this.setState({
        lng: map.getCenter().lng.toFixed(4),
        lat: map.getCenter().lat.toFixed(4),
        zoom: map.getZoom().toFixed(2)
      });
    });
  }

  render() {
    return (
      <div>
        <div className='sidebarStyle'>
          <div>Longitude: {this.state.lng} | Latitude: {this.state.lat} | Zoom: {this.state.zoom}</div>
        </div>
        <div ref={el => this.mapContainer = el} className='mapContainer' />
      </div>
    )
  }
}

export default Map