import React from 'react';
import Link from '../components/Link';
import {faBars} from '@fortawesome/free-solid-svg-icons';
import {FontAwesomeIcon} from '@fortawesome/react-fontawesome';


function Header() {
  const [navbarOpen, setNavbarOpen] = React.useState(false);
  const links = [
    {
      label: 'Min side',
      url: '#responsive-header',
    },
    {
      label: 'Aksjer',
      url: '#responsive-header',
    },
    {
      label: 'Hjelp',
      url: '#responsive-header',
    },
    {
      label: 'Blog',
      url: '#responsive-header',
    },
  ];

  return (
    <nav className="flex flex-wrap items-center justify-between bg-gray-100 p-6 text-gray-700">

      <div className="flex items-center flex-no-shrink text-indigo-900 mr-6">
        <img
          src="/logo192.png"
          width="30"
          height="30"
          className=""
          alt=""/>
        <span className="font-semibold ml-2 text-xl">Aksjesimulator</span>
      </div>

      <div className="flex md:hidden">
        <button className="text-xl text-indigo-900 hover:text-red-400"
          onClick={() => setNavbarOpen(!navbarOpen)}>
          <FontAwesomeIcon icon={faBars}/>
        </button>
      </div>

      <div className={'w-full md:w-auto text-center font-semibold' + (navbarOpen ? '' : ' hidden') + ' md:flex'}>
        {links.map((link) => {
          return <>
            <Link url={link.url} className="block md:inline-block mt-2 ml-4 border-b-2 border-red-200">
              {link.label}
            </Link>
          </>;
        })}
      </div>
    </nav>
  );
}


export default Header;
