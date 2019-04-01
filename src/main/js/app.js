'use strict';

import Calculator from './components/calculator'
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
		this.request = new Requests();
		this.state = { 
			numbers: [],
			operation: [],
			lastMessage: "",
			isFirstNumberAdded: false,
			data: []
		};
		this.addOperands = this.addOperands.bind(this);
		this.addOperation = this.addOperation.bind(this);
		this.request.getNewSession();
	}

	componentDidMount() {
	}

	addOperands(event) {
        event.preventDefault();
        const form = event.target
        const data = new FormData(event.target);
        
        var object = {};
        data.forEach(function(value, key){
            object[key] = value;
        });
        if(!(object.numbers) || object.numbers == "" )
			return;
		object.numbers = object.numbers.split(',');
		object.numbers = object.numbers.map(item => item.trim());
		
		const reqObj = object.numbers;
		var json = JSON.stringify(reqObj);
		var us = this;
		//object.numbers.push.apply(object.numbers, this.state.numbers);
		this.request.addOperands(reqObj).then(function(data){
			if(data == "OK"){
				us.setState({ 
					numbers : object.numbers,
					isFirstNumberAdded: true,
					lastMessage: "Added correctly"
				});
			}else{
				us.setState({ lastMessage: data.message });
			}
		});	
	}

	addOperation(event) {
        event.preventDefault();
        const form = event.target
        const data = new FormData(event.target);
        
        var object = {};
        data.forEach(function(value, key){
            object[key] = value;
		});
		const { numbers } = this.state;
		if( numbers == null || numbers.length < 1){
			this.setState({ lastMessage: "Please type at least one number before trying to get a result."});
			return;
		}
        if(!(object.operation) || object.operation == "" )
			return;
		const reqObj = object.operation;
		var us = this;
		
		this.request.addOperation(reqObj).then(function(data){
			if(data == "OK"){
				us.setState({ 
					numbers : [],
					lastMessage: "Added correctly" 
				});
				us.request.calculate().then(function(datas){
					if(datas.message){
						us.setState({ lastMessage : datas.message });
					}else{
						let data = datas.calculations.map((item, idx) => 
							<tr>
								<td>{idx}</td>
								<td>{JSON.stringify(item.numbers)}</td>
								<td>{item.appOperation.name}</td>
							</tr>
						);
						us.setState({ 
							lastMessage: "Acutual result: " + datas.result,
							data: data
						});
					}
				});	
			}else{
				us.setState({ lastMessage: data.message });
			}
		});	
	}

	render() {
		const {lastMessage} = this.state;
		return (
			<div>
				<RouterTab/>
				<div className="content">
					<Calculator addOperands={this.addOperands} addOperation={this.addOperation} 
					lastMessage={lastMessage} data={this.state.data}/>
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


