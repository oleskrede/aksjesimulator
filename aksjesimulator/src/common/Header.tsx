import React from 'react';
import Link from '../components/Link';


function Header() {
  const links = [
    {
      label: 'Aksjer',
      url: '#responsive-header',
    },
    {
      label: 'Docs',
      url: '#responsive-header',
    },
    {
      label: 'Examples',
      url: '#responsive-header',
    },
    {
      label: 'Blog',
      url: '#responsive-header',
    },
  ];

  return (
    <nav className="flex items-center justify-between flex-wrap bg-gray-100 p-6 text-black">

      <div className="flex items-center flex-no-shrink text-red-400 mr-6">
        <img
          src="/logo192.png"
          width="30"
          height="30"
          className=""
          alt=""/>
        <span className="font-semibold ml-2 text-xl tracking-tight">Aksjesimulator</span>
      </div>

      <div className="block lg:hidden">
        <button className="flex items-center px-3 py-2 border rounded text-black border-black
                              hover:text-red-200 hover:border-red-400">
          <svg className="h-3 w-3" viewBox="0 0 20 20" xmlns="http://www.w3.org/2000/svg">
            <title>Menu</title>
            <path d="M0 3h20v2H0V3zm0 6h20v2H0V9zm0 6h20v2H0v-2z"/>
          </svg>
        </button>
      </div>

      <div className="w-full block flex-grow lg:flex lg:items-center lg:w-auto">
        <div className="text-sm lg:flex-grow">
          {links.map((link) => {
            return <>
              <Link url={link.url} className="block mt-4 lg:inline-block lg:mt-0 mr-4">
                {link.label}
              </Link>
            </>;
          })}
        </div>
      </div>
    </nav>
  );
}


export default Header;
