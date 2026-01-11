# Architecture Overview
This section documents the high-level and detailed architecture of
the Sound2Text project.
It includes conceptual layers, module boundaries, dependency
injection flow, and runtime behavior.
---
## Table of Contents
### 1. Conceptual Architecture
- [Layered Architecture](diagrams/layered-architecture.md)
- [Module Boundaries](diagrams/module-boundaries.md)
### 2. Runtime Behavior
- [Dependency Injection Flow](diagrams/di-flow.md)
- [Runtime Sequence Diagram](diagrams/runtime-sequence.md)
### 3. Architecture Models
- [Context Diagram](../models/context-diagram.uma)
- [Container Diagram](../models/container-diagram.uma)
- [Full Architecture Model](../models/architecture.uma)
---
## Overview
Provide a short narrative here describing the system’s conceptual
layers:
- **Configuration Layer** — AppSettings, SpeechToTextSettings, engine
  configs
- **Core Logic Layer** — structured text processing, utilities,
  domain logic
- **STT Engine Layer** — Vosk, Whisper, Azure, Google engines
- **UI Layer** — Desktop UI and Android UI
  This section should give readers a sense of the system’s shape before
  they dive into diagrams.
---
## Modules
Briefly describe each module:
### `:config`
Engine-agnostic configuration, settings, and defaults.
### `:core`
Pure logic, structured text processing, utilities.
### `:stt`
Engine abstraction, engine configs, platform-specific
implementations.
### `:desktop-ui`
Desktop application UI.
### `:android-ui`
Android application UI.
---
## Diagrams
