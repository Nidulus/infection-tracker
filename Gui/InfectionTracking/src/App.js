import React, {Component} from 'react';
//import styled from 'styled-components'
import Navbar from './components/Navbar'
import Map from './pages/maps';
import './App.css';
import {Accordion, Icon, Button, Statistic, Form, Grid, Segment, Container, Header} from 'semantic-ui-react'

const style = {
  h1: {
    marginTop: '3em'
  },
  h2: {
    margin: '4em 0em 2em',
  },
  h3: {
    marginTop: '3em',
    padding: '2em 0em',
  },
  last: {
    marginBottom: '300px',
  },
}

export default class AccordionExampleFluid extends Component {
  state = { activeIndex: 1 }

  handleClick = (e, titleProps) => {
    const { index } = titleProps
    const { activeIndex } = this.state
    const newIndex = activeIndex === index ? -1 : index

    this.setState({ activeIndex: newIndex })
  }

  render() {
    const { activeIndex } = this.state

    return (

      <div>
        <Navbar/>

        <Container text style={{ marginTop: '7em' }}>
          <Header as='h1'>Semantic UI React Fixed Template</Header>
          
        </Container>
        
        <Container text style={{ marginTop: '7em', marginLeft:'7em', marginBottom: '4em' }}>

          <Statistic.Group>
            <Statistic color='red'>
              <Statistic.Value>2 780</Statistic.Value>
              <Statistic.Label>Infected</Statistic.Label>
            </Statistic>

            <Statistic color='yellow'>
              <Statistic.Value>280</Statistic.Value>
              <Statistic.Label>Symptomatic</Statistic.Label>
            </Statistic>


            <Statistic color='green'>
              <Statistic.Value>1 480</Statistic.Value>
              <Statistic.Label>Immunized</Statistic.Label>
            </Statistic>
          </Statistic.Group>
        </Container>

        <Grid columns={2} stackable>
          <Grid.Column>
            <Segment>
              <Accordion fluid styled>
                <Accordion.Title
                  active={activeIndex === 0}
                  index={0}
                  onClick={this.handleClick}
                >
                  <Icon name='dropdown' />
                  Search Options
                </Accordion.Title>
                <Accordion.Content active={activeIndex === 0}>
                  <Form>
                    <Form.Group widths='equal'>
                      <Form.Field label='Citizen_id' control='input' />
                      <Form.Field label='Infection Type' control='select'>
                        <option value='COVID-1'>COVID-1</option>
                        <option value='COVID-2'>COVID-2</option>
                        <option value='COVID-3'>COVID-3</option>
                        <option value='COVID-4'>COVID-4</option>
                        <option value='COVID-5'>COVID-5</option>
                        <option value='COVID-6'>COVID-6</option>
                        <option value='COVID-19'>COVID-19</option>
                      </Form.Field>
                    </Form.Group>
                    <Form.Group grouped>
                      <label>HTML radios</label>
                      <Form.Field
                        label='Infected'
                        control='input'
                        type='radio'
                        name='htmlRadios'
                      />
                      <Form.Field
                        label='Immunisezed'
                        control='input'
                        type='radio'
                        name='htmlRadios'
                      />
                      <Form.Field
                        label='Reported symptoms'
                        control='input'
                        type='radio'
                        name='htmlRadios'
                      />
                    </Form.Group>
                    <Form.Field label='' control='button'>
                      Search
                    </Form.Field>
                  </Form>
                </Accordion.Content>
              </Accordion>
            </Segment>
          </Grid.Column>

          <Grid.Column>
            <Segment>
              <Map/>
            </Segment>
          </Grid.Column>
        </Grid>
      </div>

    )
  }
}
