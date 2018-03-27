import React, {Component} from 'react'
import {CSSTransitionGroup} from 'react-transition-group'
import {Link} from 'react-router-dom'

const URL_DEPUTIES = 'http://localhost:8080/';

class ListofDeputies extends Component {

    constructor(props){
        super(props);
        this.state = {
            deputies: [],
            filtered: [],
            filteredClub: this.props.clubs,
            keyword: ''
        }
    }

    componentDidMount() {
        fetch(`${URL_DEPUTIES}`)
        .then(response => response.json())
        .then(json => this.sortAlphabeticaly(json))
        .then(sortedjson => {
            this.setState({
                deputies: sortedjson,
                filtered: sortedjson
            })
        })
    }

    sortAlphabeticaly = (list) => {
        return list.sort((function(first, second){
            if(first.name < second.name) return -1;
            if(first.name < second.name) return 1;
            return 0;
        }))
    }

    searchDeputy = (event) => {
        const keyword = event.target.value;
        console.log(event.target.value);
        if(keyword !== '') {
            const list = this.state.deputies.filter((item) => {
                return (item.name.toLowerCase().indexOf(keyword.toLowerCase()) > -1 && this.isButtonHighlighted(item.club));
            })
            this.setState({
                filtered: list,
                keyword: event.target.value
            })
        } else {
            this.setState({
                filtered: this.state.deputies,
                keyword: keyword
            })
        }
    }

    isButtonHighlighted = (club) => {
        let shortcut = this.props.clubMapping[club];
        return this.props.clubs.includes(shortcut);
    }

    getMappedValue = (item) => {
        return this.props.clubMapping[item];
    }


    renderList = ({filtered, filteredClub}) => {
        console.log(filteredClub);
        return filtered.map((item) => {
            if(this.isButtonHighlighted(item.club)) {
                return (
                    <Link to={`/deputy/${item.id}`} className="deputy_item" key={item.id} style={{ textDecoration: 'none' }}>
                        <div className="single_deputy" key={item.id}>
                            <div className="deputy-name">
                                <p>{item.name}</p>
                            </div>
                            <div className="deputy-club">
                                <p>{this.getMappedValue(item.club)}</p>
                            </div>
                        </div>
                    </Link>
                )
            }
        })
    }

    render() {
        return(
            <div className="deputies_component">
                <div className="search_component">
                    <input type="text" placeholder="Wyszukaj posła.."
                    onChange={(e) => this.searchDeputy(e)}/>
                </div>
                <div className="deputies_container">
                    {this.renderList(this.state)}
                </div>
            </div>
        )
    }
}

export default ListofDeputies;
