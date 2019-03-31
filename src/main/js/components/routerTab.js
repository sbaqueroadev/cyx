'use strict';

import React from 'react';
import { render as _render } from 'react-dom';
import client from '../client'

class RouterTab extends React.Component{

    constructor(){
        super();
    }

    render(){
        const { rol } = this.state;
        let options;
        options = <ul className="nav navbar-nav">
                    <li><a href="../logout">Cerrar Sesión</a></li>
                  </ul>
        }
        
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
                        <a className="navbar-brand" href="#">Lecturas para tu salud</a>
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