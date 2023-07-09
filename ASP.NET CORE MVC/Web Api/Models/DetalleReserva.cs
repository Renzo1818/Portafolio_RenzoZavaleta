using System;
using System.Collections.Generic;

namespace API_BIBLIO.Models;

public partial class DetalleReserva
{
    public int IdReserva { get; set; }

    public int IdLibro { get; set; }

    public int Cantidad { get; set; }

    public bool Estado { get; set; }

    public virtual Libro IdLibroNavigation { get; set; } = null!;

    public virtual ReservaVirtual IdReservaNavigation { get; set; } = null!;
}
