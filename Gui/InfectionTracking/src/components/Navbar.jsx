import React from 'react'
import styled from 'styled-components'
//import { Container } from 'semantic-ui-react'
//import { NavLink } from 'react-router-dom'

const Container = styled.header`
z-index: 100;
  position: fixed;
  top: 0;
  left: 0;
  right: 0;
  max-width: 100vw;
  margin-left: auto!important;
  margin-right: auto!important;
  padding: 1em 2em;
  display: flex;
  justify-content: space-between;
  align-items: center;
  color: #efefef;
  background-color: #222222;
`
const Header = styled.header`
  z-index: 100;
  display: flex;
  justify-content: space-between;
  left: auto;
  right: auto;
  margin-left: auto!important;
  margin-right: auto!important;

`

const Brand = styled.h1`
  font-size: var(--step-up-1);
`

const Menu = styled.ul`
  display: flex;
  justify-content: flex-end;
  align-items: center;
  width: 50vw;
`

const MenuLink = styled.li`
  margin-left: 2em;
  text-decoration: none;
`

export default () => (
  <Container>
    <Header>
      <Brand>Infection Tracker</Brand>
      <Menu fixed='top' inverted>
        <MenuLink>
            Home
        </MenuLink>
        <MenuLink>
            About
        </MenuLink>
      </Menu>
    </Header>
  </Container>
)