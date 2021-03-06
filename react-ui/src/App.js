import logo from './logo.svg';
import './App.css';
import {BrowserRouter as Router, Route, Switch} from 'react-router-dom';
import ListEmployeeComponent from './components/ListEmployeeComponent';
import HeaderComponent from './components/HeaderComponent';
import FooterComponent from './components/FooterComponent';
import CreateEmployeeComponent from './components/CreateEmployeeComponent';
import ViewEmployeeComponenet from './components/ViewEmployeeComponenet';

function App() {
  // this is the react functional componenet
  return (
    // jsx code [JavaScript xml code].
    <div>
      <Router>
      <HeaderComponent />
      <div className="container">
        <Switch>
          <Route path = "/" exact component = {ListEmployeeComponent}></Route>
          <Route path = "/allEmployees" component = {ListEmployeeComponent}></Route>
          <Route path = "/addEmployee/:id" component = {CreateEmployeeComponent}></Route>
          <Route path = "/viewEmployee/:id" component = {ViewEmployeeComponenet}></Route>
        </Switch>
      </div>
      <FooterComponent />
      </Router>
    </div>
  );
}

export default App;
