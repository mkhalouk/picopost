/** @jsxImportSource @emotion/react */
import { css, keyframes } from '@emotion/react';
import { Box } from '@mui/material';

const twinkle = keyframes`
  0% { opacity: 0; }
  50% { opacity: 1; }
  100% { opacity: 0; }
`;

const Star = ({ top, left }) => (
  <div
    css={css`
      width: 0.2em;
      height: 0.2em;
      background: white;
      position: absolute;
      border-radius: 50%;
      top: ${top}vh;
      left: ${left}vw;
      animation: ${twinkle} 2s ease-in-out infinite both;
      animation-delay: ${Math.random() * 2}s;
    `}
  />
);

export default function Stars() {
  const stars = [];

  for (let i = 0; i < 100; i++) {
    const top = Math.floor(Math.random() * 100);
    const left = Math.floor(Math.random() * 100);
    stars.push(<Star key={i} top={top} left={left} />);
  }

  return (
    <Box
      sx={{
        position: 'absolute',
        top: 0,
        left: 0,
        width: '100vw',
        height: '100vh',
        bgcolor: 'black',
        overflow: 'hidden',
        zIndex: -1,
      }}
    >
      {stars}
    </Box>
  );
}
