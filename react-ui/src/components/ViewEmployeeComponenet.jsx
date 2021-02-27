import React, { Component } from 'react'
import { PersonLinesFill } from 'react-bootstrap-icons';
import EmployeeService from '../services/EmployeeService';

export default class ViewEmployeeComponenet extends Component {
    constructor(props) {
        super(props);

        this.state = {
            id: this.props.match.params.id,
            employee: {}
        }
    }

    componentDidMount() {
        EmployeeService.getEmployeeById(this.state.id).then((resp) => {
            this.setState({ employee : resp.data});
        });
    }

    render() {
        return (
            <div>
                <br />
                <div className="card col-md-6 offset-md-3">
                    <br/>
                    <h3 className="text-center"><PersonLinesFill /> Employee Details :</h3>
                    <div className="card-body">
                    <div className="row">
                        <label>First Name : </label>
                        <div> {this.state.employee.firstName}</div>
                    </div>
                    <div className="row">
                        <label>Last Name : </label>
                        <div> {this.state.employee.lastName}</div>
                    </div>
                    <div className="row">
                        <label>Email ID : </label>
                        <div> {this.state.employee.emailId}</div>
                    </div>
                </div>
                </div>
            </div>
        )
    }
}
