using BIBLIOTECA_VIRTUAL.Models;

namespace BIBLIOTECA_VIRTUAL.Services.Tareas
{
    public class DevolucionServidor: BackgroundService
    {
        private OperaC conexion = new OperaC();
        private readonly IServiceScopeFactory _serviceScopeFactory;

        public DevolucionServidor(IServiceScopeFactory serviceScopeFactory)
        {
            _serviceScopeFactory = serviceScopeFactory;
        }

        protected override async Task ExecuteAsync(CancellationToken stoppingToken)
        {
            while (!stoppingToken.IsCancellationRequested)
            {
                using (var scope = _serviceScopeFactory.CreateScope())
                {

                    // Obtén los libros prestados
                    var librosPrestados = (from tbReserva in conexion.ReservaVirtuals where tbReserva.Estado == false select tbReserva).ToList();

                    foreach (var libro in librosPrestados)
                    {
                        var librosDetalle = (from tbDetalleReserva in conexion.DetalleReservas where tbDetalleReserva.IdReserva == libro.IdReserva &&
                                               tbDetalleReserva.Estado == false 
                                               select tbDetalleReserva).ToList();
                        if (libro.FechaFinal < DateTime.Now) //18:28:00 - 18:25:01
                        {
                            foreach(var libroD in librosDetalle)
                            {
                                libroD.Estado = true;
                            }
                            
                            libro.Estado = true;
                            // Actualizar el estado del libro en la base de datos
                            conexion.SaveChanges();

                            
                        }
                    }
                }

                await Task.Delay(TimeSpan.FromMinutes(1), stoppingToken); // Verificar cada hora (ajusta según tus necesidades)
            }
        }
    }
}
