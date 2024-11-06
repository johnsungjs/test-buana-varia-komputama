/** @type {import('tailwindcss').Config} */
module.exports = {
  content: [
    "./src/**/*.{js,jsx,ts,tsx}",
  ],
  theme: {
    container: {
      center: true,
      padding: '16px',
    },
    extend: {
      colors: {
        primary: '#14b8a5',
        secondary: '#64748b',
        dark: '#0f172a',
        orangeMega: '#FA9D1C',
        orangeTuaMega: '#f37022',
        kuningMega: '#f37022',
        abuMega:'#f37022',
        abuMudaMega:'#9e9fa3'
      },
      screens: {
        '2xl': '1320px',
      }
    },
  },
  plugins: [],
}

