import React from 'react';
import { render as _render } from 'react-dom';

class Calculator extends React.Component{

    render(){
        return (
                <div>
                    <form onSubmit={(e) => this.props.addOperands(e)}>
                    <label htmlFor="numbers">Add numbers:</label>
                        <input 
                            name="numbers"
                            id="numbers"
                            type="text"
                            placeholder="separate numbers by comas e.g: 1,2,3">
                        </input>
                        <button 
                            type="submit">Add
                        </button>
                    </form>
                    <form onSubmit={(e) => this.props.addOperation(e)}>
                        <label htmlFor="operation">Select the operation:</label>
                        <select 
                            name="operation"
                            id="operation">
                            <option value="s">Addition</option>
                            <option value="m">Substraction</option>
                            <option value="mu">Multiplication</option>
                            <option value="d">Division</option>
                            <option value="p">Pow</option>
                        </select>
                        <button 
                            type="submit">Calculate
                        </button>
                    </form>
                    <div>
                        {this.props.lastMessage}
                    </div>
                    <table>
                        <thead>
                            <tr>
                                <th>#</th>
                                <th>Operands</th>
                                <th>Operation</th>
                            </tr>
                        </thead>
                        <tbody>
                            {this.props.data}
                        </tbody>
                    </table>
                </div>
            );
    }
}

export default Calculator;