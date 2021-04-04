import React, { Component } from 'react'
import { Trash2, PencilSquare, PersonPlusFill, EyeFill } from 'react-bootstrap-icons';
import MUIDataTable from 'mui-datatables';
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
            this.setState({ employees: res["data"] });
        });
    };
    render() {

        const columns = [
            {
             name: "firstName",
             label: "First Name",
             options: {
                filter: true,
                sort: true,
             }
            },
            {
             name: "lastName",
             label: "Last Name",
             options: {
                filter: true,
                sort: true,
             }
            },
            {
             name: "emailId",
             label: "Email ID",
             options: {
                filter: false,
                sort: true,
             }
            },
            {
             name: "id",
             label: "Action",
             options: {
                filter: false,
                sort:false,
                customBodyRender: (value, tableMeta, updateValue) => (
                    <span>
                        <button onClick={() => this.deleteEmployee(value)} className="row-btn delete-btn"><Trash2 size={15} /></button>
                        <button onClick={() => this.editEmployee(value)} className="row-btn edit-btn"><PencilSquare size={13} /></button>
                        <button onClick={() => this.viewEmployee(value)} className="row-btn view-btn"><EyeFill size={15} /></button>
                    </span>
                )
             }
            },
           ];

        const options = {
            filter: true,
            responsive: 'standard',
        };
           
           const data = this.state.employees;
        return (
            <div>
                <h2 className="text-center">Employees List :</h2>
                <div className="row">
                    <button className="btn btn-success btn-sm row-btn mb-1" onClick={this.addEmployee}><PersonPlusFill /> Add</button>
                </div>
                <br/>
                <MUIDataTable
                    title={"Employee List"}
                    data={data}
                    columns={columns}
                />
                {/* <div className="row">
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
                </div> */}
            </div>
        )
    }
}
