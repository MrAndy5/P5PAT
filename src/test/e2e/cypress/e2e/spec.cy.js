describe('Registro y login', () => {
  const email = Date.now() + '@email.com'
  const pass = 'aaaaaaA1';
  const name = 'Mr.Nombre'

  it('Registro correcto', () => {
    cy.visit('http://localhost:8080/registro.html')
    cy.get('[name="name"]').type(name)
    cy.get('[name="name"]').should('have.value', name)
    cy.get('[name="email"]').type(email)
    cy.get('[name="email"]').should('have.value', email)
    cy.get('[name="password"]').type(pass)
    cy.get('[name="password"]').should('have.value', pass)
    cy.get('[name="password2"]').type(pass)
    cy.get('[name="password2"]').should('have.value', pass)
    cy.contains('Registrarse').click()
    cy.url().should('include', '/login.html')
    cy.contains('¡Registrado! Prueba a entrar')
  })

  // TODO#13
  // Implementa el siguiente test E2E del frontend web para
  // verificar que se realiza el login correctamente con el usuario
  // previamente registrado
  it('Login correcto', () => {
    cy.visit('http://localhost:8080/login.html');

  cy.get('#email').type(email);
  cy.get('#password').type(pass);
  cy.get('input[type="submit"]').click();

  // Verifica que se redirige a app.html
  cy.url().should('include', '/app.html');

  // Verifica que el nombre del usuario aparece en #nombre-inicio
  cy.get('#nombre-inicio').should('contain', name);

  // Verifica que el correo se muestra correctamente
  cy.get('#email-inicio').should('contain', email);

  // Verifica que el rol de usuario aparece (si el campo id es correcto)
  cy.get('#tel-inicio').should('contain', 'USER');

  //Con esto habrá veririficado que el login ha sido correcto y redigirá a la página principal de la app, que mostrará los datos del usuario
  })
})
