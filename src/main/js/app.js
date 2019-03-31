'use strict';

import Paciente from './components/calculator'
import Requests from './api/request'
import RouterTab from './components/routerTab';

// tag::vars[]
const React = require('react');
const ReactDOM = require('react-dom');
const client = require('./client');
// end::vars[]

// tag::app[]
class App extends React.Component {

	constructor(props) {
		super(props);
		this.gRequest = new GetRequests();
		this.state = {
			calculation: {
				numbers: []
				operation: []
			}
		};
	}

	componentDidMount() {
	}

	render() {
		return (
			<div>
				<RouterTab/>
				<div className="content">
					Content
				</div>
			</div>
		)
	}
}
// end::app[]

// tag::render[]
ReactDOM.render(
	<App />,
	document.getElementById('react')
)
// end::render[]


