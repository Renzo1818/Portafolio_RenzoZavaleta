using System;
using System.Collections.Generic;

namespace API_BIBLIO.Models;

public partial class Autor
{
    public int IdAutor { get; set; }

    public string NombreApellido { get; set; } = null!;

    public DateTime FechaNacimiento { get; set; }

    public string Nacionalidad { get; set; } = null!;

    public bool Estado { get; set; }

    public virtual ICollection<Libro> Libros { get; set; } = new List<Libro>();
}
