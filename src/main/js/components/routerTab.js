'use strict';

import React from 'react';
import { render as _render } from 'react-dom';
import client from '../client'

class RouterTab extends React.Component{

    constructor(){
        super();
    }

    //<li><a href="../logout">Cerrar Sesión</a></li>-->
    render(){
        let options;
        options = <ul className="nav navbar-nav">
                  </ul>;
        
        return(
             <nav className="navbar navbar-inverse navbar-fixed-top">
                <div className="container">
                    <div className="navbar-header">
                        <button type="button" className="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar"
                                aria-expanded="false" aria-controls="navbar">
                            <span className="sr-only">Toggle navigation</span>
                            <span className="icon-bar"></span>
                            <span className="icon-bar"></span>
                            <span className="icon-bar"></span>
                        </button>
                        <a className="navbar-brand" href="#">Calculator</a>
                    </div>
                    <div id="navbar" className="collapse navbar-collapse">
                        {options}
                    </div>
                </div>
            </nav>
        );
    }
}

export default RouterTab;