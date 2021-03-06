import React from 'react';

export interface LinkProps {
  url: string,
  className?: string,
  children?: React.ReactNode
}

const Link = (props: LinkProps) =>
  <a
    href={props.url}
    className={'text-indigo-900 hover:text-red-400 ' + props.className}>
    {props.children}
  </a>;


export default Link;
