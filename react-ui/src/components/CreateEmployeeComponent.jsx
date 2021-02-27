import React, { Component } from 'react';
import { PersonCheckFill, PersonPlus } from 'react-bootstrap-icons';
import EmployeeService from '../services/EmployeeService';

export default class CreateEmployeeComponent extends Component {
    constructor(props) {
        super(props);
        this.state = {
            id: this.props.match.params.id,
            firstName: "",
            lastName: "",
            emailId: ""
        }
        this.changeFirstNameHandler = this.changeFirstNameHandler.bind(this);
        this.changeLastNameHandler = this.changeLastNameHandler.bind(this);
        this.saveOrUpadateEmployee = this.saveOrUpadateEmployee.bind(this);
    }

    componentDidMount(){
        if(this.state.id === "_add"){
            return;
        } else {
            EmployeeService.getEmployeeById(this.state.id).then( resp => {
                let employee = resp.data;
                this.setState({
                    firstName: employee.firstName,
                    lastName: employee.lastName,
                    emailId: employee.emailId
                });
            });
        }
    }

    saveOrUpadateEmployee = (event)=>{
        event.preventDefault();

        let employee = {
            firstName: this.state.firstName,
            lastName: this.state.lastName,
            emailId: this.state.emailId
        };

        if(this.state.id === '_add'){
            EmployeeService.createEmployee(employee).then( res => {
                this.props.history.push("/allEmployees")
            });
        } else if(this.state.id != undefined) {
            EmployeeService.updateEmployee(employee, this.state.id).then((resp)=>{
                console.log(resp);
                this.props.history.push("/allEmployees");
            });
        }
    }
    cancel = () =>{
        this.props.history.push("/allEmployees");
    }
    changeFirstNameHandler = (event) => {
        this.setState({ firstName: event.target.value });
    }
    changeLastNameHandler = (event) => {
        this.setState({ lastName: event.target.value });
    }
    changeEmailIdHandler = (event) => {
        this.setState({ emailId: event.target.value });
    }

    getTitle(){
        if(this.state.id === '_add'){
            return <h3><PersonPlus /> Add Employee </h3>
        } else {
           return <h3><PersonCheckFill /> Update Employee </h3>
        }
    }

    render() {
        return (
            <div>
                <div className="container">
                    <div className="row">
                        <div className="card col-md-6 offset-md-3">
                            {this.getTitle()}
                            <div className="card-body">
                                <form>
                                    <div className="form-group">
                                        <label>First Name : </label>
                                        <input placeholder="First Name" type="text" name="firstName" className="form-control"
                                            value={this.state.firstName} onChange={this.changeFirstNameHandler} />
                                    </div>
                                    <div className="form-group">
                                        <label>Last Name : </label>
                                        <input placeholder="Last Name" type="text" name="lastName" className="form-control"
                                            value={this.state.lastName} onChange={this.changeLastNameHandler} />
                                    </div>
                                    <div className="form-group">
                                        <label>Email-Id : </label>
                                        <input placeholder="abc@example.com" type="text" name="emailId" className="form-control"
                                            value={this.state.emailId} onChange={this.changeEmailIdHandler} />
                                    </div>
                                    <button className="btn btn-success" onClick={this.saveOrUpadateEmployee}>Save</button>
                                    <button className="btn btn-danger" onClick={this.cancel.bind(this)} style={{marginLeft: "10px"}}>Cancel</button>
                                </form>
                            </div>
                        </div>

                    </div>
                </div>
            </div>
        )
    }
}
