import React, { Component } from 'react';
import {People} from 'react-bootstrap-icons';

export default class HeaderComponent extends Component {
    constructor(props){
        super(props);

        this.state = {

        }
    }

    render() {
        return (
            <div>
                <header>
                    <nav className="navbar navbar-expand-md navbar-dark bg-dark">
                        <div href="#" className="navbar-brand"><People /> Employees Management App</div>
                    </nav>
                </header>
                
            </div>
        )
    }
}
