import React from "react";

const About = () => {
  return (
    <div className="min-h-screen bg-gray-50 text-gray-900 px-6 py-16 flex items-center">
      <div className="max-w-3xl mx-auto bg-white rounded-lg shadow-lg p-8">
        <h1 className="text-4xl font-extrabold text-blue-700 mb-8 text-center">
          À propos
        </h1>
        <p className="text-lg leading-relaxed mb-6">
          Cette application a été développée pour faciliter la gestion des incidents techniques à l’aéroport Marrakech Menara.
        </p>
        <p className="text-lg leading-relaxed mb-6">
          L'objectif est de permettre un suivi efficace, une organisation claire des incidents, et une communication rapide entre les équipes techniques.
        </p>
        <p className="text-lg leading-relaxed mb-6">
          Ce projet a été réalisé dans le cadre d’un stage à l’ONDA, et il reflète les besoins réels du terrain.
        </p>
        <div className="mt-10 border-t pt-6">
          <h2 className="text-2xl font-semibold text-blue-600 mb-3">Contact</h2>
          <p className="mb-2">
            Email :{" "}
            <a
              href="mailto:charouqrehab@gmail.com"
              className="text-blue-600 underline hover:text-blue-800"
            >
              charouqrehab@gmail.com
            </a>
          </p>
          <p>
            GitHub :{" "}
            <a
              href="https://github.com/rihab"
              target="_blank"
              rel="noopener noreferrer"
              className="text-blue-600 underline hover:text-blue-800"
            >
              github.com/rihab
            </a>
          </p>
        </div>
      </div>
    </div>
  );
};

export default About;