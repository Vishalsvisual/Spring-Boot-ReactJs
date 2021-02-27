import React, { Component } from 'react'
import { Trash2, PencilSquare, PersonPlusFill, EyeFill } from 'react-bootstrap-icons';
import EmployeeService from '../services/EmployeeService';

export default class ListEmployeeComponent extends Component {

    constructor(props) {
        super(props)

        this.state = {
            employees: []
        }
        this.addEmployee = this.addEmployee.bind(this);
        this.editEmployee = this.editEmployee.bind(this);
        this.deleteEmployee = this.deleteEmployee.bind(this);
    }

    viewEmployee(id){
        this.props.history.push(`/viewEmployee/${id}`);
    }

    editEmployee(id) {
        this.props.history.push(`/addEmployee/${id}`);
    }

    deleteEmployee(id) {
        EmployeeService.deleteEmployee(id).then((resp) => {
            console.log(resp);
            this.setState({ employees: this.state.employees.filter(employee => employee.id !== id) });
        })
    }

    addEmployee() {
        this.props.history.push("/addEmployee/_add");
    }

    componentDidMount() {
        EmployeeService.getEmployees().then((res) => {
            this.setState({ employees: res["data"] })
        });
    };
    render() {
        return (
            <div>
                <h2 className="text-center">Employees List :</h2>
                <div className="row">
                    <button className="btn btn-success btn-sm row-btn mb-1" onClick={this.addEmployee}><PersonPlusFill /> Add</button>
                </div>
                <br/>
                <div className="row">
                    <table className="table table-striped table-bordered">
                        <thead>
                            <tr>
                                <th>First Name</th>
                                <th>Last Name</th>
                                <th>Email-ID</th>
                                <th>Action</th>
                            </tr>
                        </thead>
                        <tbody>
                            {this.state.employees.map(employee =>
                                <tr key={employee.id}>
                                    <td>{employee.firstName}</td>
                                    <td>{employee.lastName}</td>
                                    <td>{employee.emailId}</td>
                                    <td className="action-data">
                                        <span>
                                            <button onClick={() => this.deleteEmployee(employee.id)} className="row-btn delete-btn"><Trash2 size={15} /></button>
                                            <button onClick={() => this.editEmployee(employee.id)} className="row-btn edit-btn"><PencilSquare size={13} /></button>
                                            <button onClick={() => this.viewEmployee(employee.id)} className="row-btn view-btn"><EyeFill size={15} /></button>
                                        </span>
                                    </td>
                                </tr>
                            )}
                        </tbody>
                        <tfoot>
                            <tr>
                                <th>First Name</th>
                                <th>Last Name</th>
                                <th>Email-ID</th>
                                <th>Action</th>
                            </tr>
                        </tfoot>
                    </table>
                </div>
            </div>
        )
    }
}
